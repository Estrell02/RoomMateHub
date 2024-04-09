#######################  User ################################
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

    def validate_password(self, value):
        try:
            validate_password(value)
        except serializers.ValidationError as e:
            raise serializers.ValidationError(list(e. messages))
        return value

    def create(self, validated_data, *args, **kwargs):
        user = User.objects.create_user(**validated_data)
        user.save()
        print(validated_data)
        return user

    def update(self, instance, validated_data):
        # Ignorer le mot de passe lors des mises à jour partielles
        password = validated_data.pop('password', None)
        user = super().update(instance, validated_data)

        if password:
            user.set_password(password)
            user.save()

        return user

class UserLoginSerializer(serializers.Serializer):
    email = serializers.EmailField()
    password = serializers.CharField(style={'input_type': 'password'})



#################### Profile   #############################


class ProfileSerializer(serializers.ModelSerializer):
    user = UserSerializer(read_only=True)
    hobbies = serializers.MultipleChoiceField(choices=Profile.HOBBY_CHOICES)
    filiere = serializers.ChoiceField(choices=Profile.FILERE_CHOICES)

    class Meta:
        model = Profile
        fields = ('user', 'picture', 'hobbies', 'vegan', 'filiere')