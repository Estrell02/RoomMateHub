#######################     User ################################
from django.contrib.auth.password_validation import validate_password
from rest_framework import serializers
from GestUsers.models import User, Profile


class UserSerializer(serializers.ModelSerializer):
    class Meta:
        model = User
        fields = ('id', 'first_name','username', 'last_name', 'email', 'password')
        extra_kwargs = {
            "password": {
                "write_only": True,
                "required": True,
            },
            "email": {
                "required": True,
            },
        }

    def validate_email(self, value):
        if value is not None:
            return value
        elif User.objects.filter(email=value).exists():
            raise serializers.ValidationError("Cette adresse e-mail est déjà utilisée.")
        return value

    def validate_username(self, value):
        if value is not None:
            return value
        elif User.objects.filter(username=value).exists():
            raise serializers.ValidationError("Ce nom d'utilisateur est déjà utilisé.")
        return value

    def create(self, validated_data, *args, **kwargs):
        user = User.objects.create_user(**validated_data)
        print(validated_data)
        return user

    def validate_password(self, value):
        try:

            validate_password(value)

        except serializers.ValidationError as e:
            raise serializers.ValidationError(list(e. messages))

        return value

class UserLoginSerializer(serializers.Serializer):
    email = serializers.EmailField()
    password = serializers.CharField(style={'input_type': 'password'})



#################### Profile   #############################


class ProfileSerializer(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)

    class Meta:
        model = Profile
        fields = ['id', 'user', 'picture','hobbies']