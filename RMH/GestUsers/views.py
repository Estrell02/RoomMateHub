from django.contrib.auth import authenticate, login, logout
from rest_framework import viewsets, status
from rest_framework.response import Response
from rest_framework.decorators import action
from rest_framework_simplejwt.tokens import RefreshToken
from django.middleware.csrf import get_token
from GestAnnounce.models import HousingApplication, Housing
from GestAnnounce.serializers import HousingApplicationSerializer
from .models import *
from .serializers import UserSerializer, ProfileSerializer
from rest_framework_simplejwt.views import TokenObtainPairView
from rest_framework.response import Response
from django.contrib.auth.decorators import login_required


class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    def partial_update(self, request, *args, **kwargs):
        instance = self.get_object()
        serializer = self.get_serializer(instance, data=request.data, partial=True)
        serializer.is_valid(raise_exception=True)
        serializer.save(password=instance.password)

        return Response(serializer.data, status=status.HTTP_200_OK)
    @action(detail=False, methods=['post'])
    def login(self, request):
        # Implémentation du login
        username = request.data.get('username')
        password = request.data.get('password')
        user = authenticate(request, username=username, password=password)
        if user is not None:
            login(request, user)
            refresh = RefreshToken.for_user(user)
            csrf_token = get_token(request)
            return Response({"id": user.id, "refresh": str(refresh), "access": str(refresh.access_token)},#"csrf_token": csrf_token},
                            status=status.HTTP_200_OK)
        else:
            return Response({"detail": "Invalid credentials"}, status=status.HTTP_400_BAD_REQUEST)

    @action(detail=False, methods=['post'])
    def logout(self, request):
        logout(request)
        return Response({"detail": "Logout successful"}, status=status.HTTP_200_OK)

    @action(detail=False, methods=['post'])
    def change_password_connected(self, request):
        user = request.user
        old_password = request.data.get('old_password')
        new_password = request.data.get('new_password')

        if not user.check_password(old_password):
            return Response({'error': 'Le mot de passe actuel est incorrect.'}, status=status.HTTP_400_BAD_REQUEST)

        user.set_password(new_password)
        user.save()
        return Response({'message': 'Le mot de passe a été modifié avec succès.'}, status=status.HTTP_200_OK)

    def partial_update(self, request, *args, **kwargs):
        instance = self.get_object()
        serializer = self.get_serializer(instance, data=request.data, partial=True)
        serializer.is_valid(raise_exception=True)
        serializer.save(password=instance.password)
        return Response(serializer.data, status=status.HTTP_200_OK)

    @action(detail=True, methods=['get'], url_path='user-applications')
    def my_user_applications(self, request, pk=None):
        try:
            user = User.objects.get(pk=pk)
        except User.DoesNotExist:
            return Response({"error": "User not found"}, status=status.HTTP_404_NOT_FOUND)

        applications = HousingApplication.objects.filter(user=user)
        serializer = HousingApplicationSerializer(applications, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)

    @action(detail=True, methods=['get'], url_path='owned-housing-applications')
    def owned_housing_applications(self, request, pk=None):
        try:
            user = User.objects.get(pk=pk)
        except User.DoesNotExist:
            return Response({"error": "User not found"}, status=status.HTTP_404_NOT_FOUND)

        housings = Housing.objects.filter(owner=user)
        applications = HousingApplication.objects.filter(announce__in=housings)
        serializer = HousingApplicationSerializer(applications, many=True)
        return Response(serializer.data, status=status.HTTP_200_OK)
class ProfileViewSet(viewsets.ModelViewSet):
    queryset = Profile.objects.all()
    serializer_class = ProfileSerializer

    def update(self, request, *args, **kwargs):
        instance = self.get_object()
        serializer = self.get_serializer(instance, data=request.data, partial=True)
        serializer.is_valid(raise_exception=True)
        if 'picture' in serializer.validated_data:
            instance.picture = serializer.validated_data['picture']
            instance.save()
        serializer.save()
        return Response(serializer.data, status=status.HTTP_200_OK)

    @action(detail=True, methods=['GET'])
    def profile_by_user_id(self, request, pk=None):
        try:
            profile = self.queryset.get(user__id=pk)
            serializer = self.get_serializer(profile)
            return Response(serializer.data)
        except Profile.DoesNotExist:
            return Response({'detail': 'Profile not found for the specified user ID.'}, status=404)

    @action(detail=True, methods=['GET'])
    def find_matching_profiles(self, request, pk=None):
        try:
            user_profile = self.queryset.get(user__id=pk)
            other_profiles = self.queryset.exclude(user__id=pk)

            similarity_scores = {}
            for profile in other_profiles:
                similarity_scores[profile.user.id] = self.calculate_similarity(user_profile, profile)

            sorted_profiles = sorted(similarity_scores.items(), key=lambda x: x[1], reverse=True)
            top_matches = sorted_profiles[:4]

            # Récupérer les profils des utilisateurs les plus similaires
            top_profiles = [self.queryset.get(user__id=match[0]) for match in top_matches]
            serializer = self.get_serializer(top_profiles, many=True)

            return Response(serializer.data)
        except Profile.DoesNotExist:
            return Response({'detail': 'Profile not found for the specified user ID.'}, status=404)

    def calculate_similarity(self, profile1, profile2):
        fields = [
            'hobbies', 'filiere', 'nightlife', 'cleanliness', 'noise_level_tolerance',
            'social_interaction_preference', 'lifestyle', 'common_area_usage', 'academic_interests',
            'consumption_habits', 'pet_friendly'
        ]

        similarity_score = 0
        total_fields = len(fields)

        for field in fields:
            value1 = getattr(profile1, field, None)
            value2 = getattr(profile2, field, None)

            if value1 and value2:
                if value1 == value2:
                    similarity_score += 1

        similarity_percentage = similarity_score / total_fields
        return similarity_percentage



class RefreshTokenView(TokenObtainPairView):
    """
    View pour rafraîchir un token JWT.
    """
    def post(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)

        # Valide le token de rafraîchissement et renvoie un nouveau token JWT
        if serializer.is_valid():
            return Response(serializer.validated_data, status=200)

        return Response(serializer.errors, status=400)


