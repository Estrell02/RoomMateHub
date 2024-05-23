from rest_framework import status, viewsets
from .serializers import *
from .models import *


class HousingViewSet(viewsets.ModelViewSet):
    queryset = Housing.objects.all()
    serializer_class = HousingSerializer
