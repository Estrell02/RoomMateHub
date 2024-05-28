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
    FILIERE_CHOICES = [
        ('math', 'Mathématiques'),
        ('sci', 'Sciences'),
        ('let', 'Lettres'),
        # Ajoutez d'autres options ici
    ]
    NIGHTLIFE_CHOICES = [
        ('night_owl', 'Noctambule'),
        ('quiet_evenings', 'Soirées calmes à la maison'),
    ]
    CLEANLINESS_CHOICES = [
        ('neat_freak', 'Très organisé(e)'),
        ('tolerant', 'Tolérant(e) envers le désordre'),
    ]
    NOISE_LEVEL_CHOICES = [
        ('sensitive', 'Sensible au bruit'),
        ('tolerant', 'Tolérant(e) envers le bruit'),
    ]
    MEAL_PREFERENCES_CHOICES = [
        ('vegan', 'Végétalien(ne)'),
        ('vegetarian', 'Végétarien(ne)'),
        ('gluten_free', 'Sans gluten'),
        ('none', 'Aucune préférence particulière'),
    ]
    SOCIAL_INTERACTIONS_CHOICES = [
        ('frequent', 'Interactions sociales fréquentes'),
        ('independent', 'Préférence pour lindépendance'),
    ]
    LIFESTYLE_CHOICES = [
        ('morning_person', 'Du matin'),
        ('night_person', 'Du soir'),
    ]
    COMMON_AREA_USAGE_CHOICES = [
        ('frequent', 'Utilisation fréquente des espaces communs'),
        ('occasional', 'Utilisation occasionnelle des espaces communs'),
    ]
    ACADEMIC_INTERESTS_CHOICES = [
        ('science', 'Sciences'),
        ('arts', 'Arts'),
        ('business', 'Affaires'),
        ('other', 'Autre'),
    ]
    CONSUMPTION_HABITS_CHOICES = [
        ('smoker', 'Fumeur(se)'),
        ('non_smoker', 'Non fumeur(se)'),
        ('social_drinker', 'Buveur(se) occasionnel(le)'),
        ('non_drinker', 'Non buveur(se)'),
    ]
    PET_FRIENDLINESS_CHOICES = [
        ('allergic', 'Allergique aux animaux'),
        ('pet_lover', 'Amateur(trice) danimaux de compagnie'),
        ('neutral', 'Neutre'),
    ]
    hobbies = models.CharField(max_length=500, choices=HOBBY_CHOICES, blank=True, null=True)
    vegan = models.BooleanField(blank=True, default=False)
    filiere = models.CharField(max_length=500, choices=FILIERE_CHOICES, blank=True, null=True)
    nightlife = models.CharField(max_length=50, choices=NIGHTLIFE_CHOICES, blank=True, null=True)
    cleanliness = models.CharField(max_length=50, choices=CLEANLINESS_CHOICES, blank=True, null=True)
    noise_level_tolerance = models.CharField(max_length=50, choices=NOISE_LEVEL_CHOICES, blank=True, null=True)
    meal_preferences = models.CharField(max_length=50, choices=MEAL_PREFERENCES_CHOICES, blank=True, null=True)
    social_interaction_preference = models.CharField(max_length=50, choices=SOCIAL_INTERACTIONS_CHOICES, blank=True,
                                                     null=True)
    lifestyle = models.CharField(max_length=50, choices=LIFESTYLE_CHOICES, blank=True, null=True)
    common_area_usage = models.CharField(max_length=50, choices=COMMON_AREA_USAGE_CHOICES, blank=True, null=True)
    academic_interests = models.CharField(max_length=50, choices=ACADEMIC_INTERESTS_CHOICES, blank=True, null=True)
    consumption_habits = models.CharField(max_length=50, choices=CONSUMPTION_HABITS_CHOICES, blank=True, null=True)
    pet_friendly = models.CharField(max_length=50, choices=PET_FRIENDLINESS_CHOICES, blank=True, null=True)
    # def __str__(self):
    #     return f"profile of {self.user.username}"
