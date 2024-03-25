from distributed import _
from django.contrib.auth.models import AbstractUser, Permission, Group
from django.db import models

class User(AbstractUser):

    pass


class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='user', unique=True)
    picture = models.ImageField(upload_to="profile/image/", blank=True, null=True)
    hobbies=models.CharField(max_length=500)


    # def __str__(self):
    #     return f"profile of {self.user.username}"