from rest_framework import status, viewsets
from rest_framework.permissions import IsAuthenticated
from .serializers import *
from rest_framework.response import Response
from .models import HousingApplication, User, Housing
from rest_framework.decorators import action


class HousingViewSet(viewsets.ModelViewSet):
    queryset = Housing.objects.all()
    serializer_class = HousingSerializer
    permission_classes = [IsAuthenticated]

    def perform_create(self, serializer):
        serializer.save(owner=self.request.user)

    def get_queryset(self):
        if self.action in ['list', 'retrieve']:
            return Housing.objects.all()
        return Housing.objects.filter(owner=self.request.user)



class HousingApplicationViewset(viewsets.ModelViewSet):
    queryset = HousingApplication.objects.all()
    serializer_class = HousingApplicationSerializer
    permission_classes = [IsAuthenticated]


    def create(self, request, *args, **kwargs):
        serializer = self.get_serializer(data=request.data)
        serializer.is_valid(raise_exception=True)
        self.perform_create(serializer)
        headers = self.get_success_headers(serializer.data)
        return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)

        serializer = self.get_serializer(housing_application)
        headers = self.get_success_headers(serializer.data)
        return Response(serializer.data, status=status.HTTP_201_CREATED, headers=headers)

    @action(detail=True, methods=['post'], url_path='approve')
    def approve_application(self, request, pk=None):
        try:
            application = HousingApplication.objects.get(pk=pk, statut='pending')
        except HousingApplication.DoesNotExist:
            return Response({"error": "Application not found or not pending"}, status=status.HTTP_404_NOT_FOUND)

        if application.announce.owner != request.user:
            return Response({"error": "You are not the owner of this housing"}, status=status.HTTP_403_FORBIDDEN)

        application.statut = 'approved'
        application.save()
        return Response({"status": "Application approved"}, status=status.HTTP_200_OK)

    @action(detail=True, methods=['post'], url_path='reject')
    def reject_application(self, request, pk=None):
        try:
            application = HousingApplication.objects.get(pk=pk, statut='pending')
        except HousingApplication.DoesNotExist:
            return Response({"error": "Application not found or not pending"}, status=status.HTTP_404_NOT_FOUND)

        if application.announce.owner != request.user:
            return Response({"error": "You are not the owner of this housing"}, status=status.HTTP_403_FORBIDDEN)

        application.statut = 'rejected'
        application.save()
        return Response({"status": "Application rejected"}, status=status.HTTP_200_OK)