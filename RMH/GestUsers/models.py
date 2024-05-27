from django.contrib.auth.models import AbstractUser, Permission, Group
from django.db import models


class User(AbstractUser):
    pass


class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='user', unique=True)
    picture = models.ImageField(upload_to="profile/image/", blank=True, null=True)
    HOBBY_CHOICES = [
        ('sports', 'Sports'),
        ('music', 'Musique'),
        ('reading', 'Lecture'),
        ('travel', 'Voyages'),
        ('others', 'Autres'),
    ]
    FILERE_CHOICES = [
        ('math', 'Mathématiques'),
        ('sci', 'Sciences'),
        ('let', 'Lettres'),
        # Ajoutez d'autres options ici
    ]
    hobbies = models.CharField(max_length=500, choices=HOBBY_CHOICES, blank=True, null=True)
    vegan = models.BooleanField(blank=True, default=False)
    filiere = models.CharField(max_length=500, choices=FILERE_CHOICES, blank=True, null=True)

    # def __str__(self):
    #     return f"profile of {self.user.username}"
