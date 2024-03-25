from django.contrib.auth import authenticate, login, logout
from django.shortcuts import render
from rest_framework.response import Response
from rest_framework import status, viewsets
from rest_framework.decorators import action
from .serializers import *
from .models import *

class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    def get_serializer_class(self):
        if self.action == 'login':
            return UserLoginSerializer
        return UserSerializer

    def partial_update(self, request, *args, **kwargs):
        instance = self.get_object()
        serializer = self.get_serializer(instance, data=request.data, partial=True)
        serializer.is_valid(raise_exception=True)
        serializer.save(password=instance.password)

        return Response(serializer.data, status=status.HTTP_200_OK)


    @action(detail=False, methods=['post'])
    def logout(self, request):
        logout(request)
        return Response({"detail": "Déconnecté avec succès."}, status=status.HTTP_200_OK)

    @action(detail=False, methods=['post'])
    def change_password_connected(self, request):
        user = self.request.user
        old_password = request.data.get('old_password')
        new_password = request.data.get('new_password')

        if not user.check_password(old_password):
            return Response({'error': 'Le mot de passe actuel est incorrect.'}, status=status.HTTP_400_BAD_REQUEST)

        user.set_password(new_password)
        user.save()

        return Response({'message': 'Le mot de passe a été modifié avec succès.'}, status=status.HTTP_200_OK)


class ProfileViewSet(viewsets.ModelViewSet):
    queryset = Profile.objects.all()
    serializer_class = ProfileSerializer

    def update(self, request, *args, **kwargs):
        instance = self.get_object()
        serializer = self.get_serializer(instance, data=request.data, partial=True)
        serializer.is_valid(raise_exception=True)

        # Si le champ 'picture' est présent dans la requête, le mettre à jour et sauvegarder
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



