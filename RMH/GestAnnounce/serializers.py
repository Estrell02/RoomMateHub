from rest_framework import serializers

from GestUsers.serializers import UserSerializer
from .models import*


class HousingSerializer(serializers.ModelSerializer):
    owner = UserSerializer(read_only=True)

    class Meta:
        model = Housing
        fields = ('id', 'title', 'description', 'price', 'location', 'photo', 'created_at', 'owner')

class HousingApplicationSerializer(serializers.ModelSerializer):
    user = serializers.PrimaryKeyRelatedField(queryset=User.objects.all())
    announce = serializers.PrimaryKeyRelatedField(queryset=Housing.objects.all())

    class Meta:
        model = HousingApplication
        fields = ['id', 'user', 'announce', 'date', 'statut']
        read_only_fields = ['date', 'statut']

    def create(self, validated_data):
        validated_data['statut'] = 'pending'
        return super().create(validated_data)