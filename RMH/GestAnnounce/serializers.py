from rest_framework import serializers

from GestUsers.serializers import UserSerializer
from .models import Housing

class HousingSerializer(serializers.ModelSerializer):
    owner = UserSerializer(read_only=True)

    class Meta:
        model = Housing
        fields = ('id', 'title', 'description', 'price', 'location', 'photo', 'created_at', 'owner')