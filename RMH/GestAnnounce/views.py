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

