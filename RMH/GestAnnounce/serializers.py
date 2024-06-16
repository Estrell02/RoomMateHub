from rest_framework import serializers

from GestUsers.serializers import UserSerializer
from .models import *


class HousingSerializer(serializers.ModelSerializer):
    owner = UserSerializer(read_only=True)
    photo_url = serializers.SerializerMethodField()

    class Meta:
        model = Housing
        fields = ('id', 'title', 'description', 'price', 'location', 'photo', 'photo_url', 'created_at', 'owner')

    def get_photo_url(self, obj):
        if obj.photo:
            return self.context['request'].build_absolute_uri(obj.photo.url)
        return None


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
