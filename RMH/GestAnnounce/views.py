from rest_framework import status, viewsets
from rest_framework.permissions import IsAuthenticated
from .serializers import *
from .models import *
from .serializers import *


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
