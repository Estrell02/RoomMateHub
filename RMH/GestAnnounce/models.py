from django.db import models
from GestUsers.models import User


# Create your models here.
class Housing(models.Model):
    title = models.CharField(max_length=200)
    description = models.TextField()
    price = models.DecimalField(max_digits=10, decimal_places=2)
    location = models.CharField(max_length=200)
    photo = models.ImageField(upload_to='housing_photos/')
    created_at = models.DateTimeField(auto_now_add=True)
    owner = models.ForeignKey(User, on_delete=models.CASCADE)

class HousingApplication(models.Model):
    user = models.ForeignKey(User, related_name='demandes_logement', on_delete=models.CASCADE)
    announce = models.ForeignKey(Housing, related_name='demandes', on_delete=models.CASCADE)
    date = models.DateField(auto_now_add=True)
    statut = models.CharField(max_length=20, choices=[('pending', 'Pending'), ('approved', 'Approved'), ('rejected', 'Rejected')])