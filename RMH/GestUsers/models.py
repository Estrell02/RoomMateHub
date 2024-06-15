from django.contrib.auth.models import AbstractUser
from django.db import models




class User(AbstractUser):
    pass




class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name='user', unique=True)
    picture = models.ImageField(upload_to="profile/image/", blank=True, null=True)
    HOBBY_CHOICES = [
        ('Sports', 'Sports'),
        ('Musique', 'Musique'),
        ('Lecture', 'Lecture'),
        ('Voyages', 'Voyages'),
        ('Autres', 'Autres'),
    ]
    FILIERE_CHOICES = [
        ('Mathématiques', 'Mathématiques'),
        ('Sciences', 'Sciences'),
        ('Lettres', 'Lettres'),

    ]
    NIGHTLIFE_CHOICES = [
        ('Noctambule', 'Noctambule'),
        ('Soirées calmes à la maison', 'Soirées calmes à la maison'),
    ]
    CLEANLINESS_CHOICES = [
        ('Très organisé(e)', 'Très organisé(e)'),
        ('Tolérant(e) envers le désordre', 'Tolérant(e) envers le désordre'),
    ]
    NOISE_LEVEL_CHOICES = [
        ('Sensible au bruit', 'Sensible au bruit'),
        ('Tolérant(e) envers le bruit', 'Tolérant(e) envers le bruit'),
    ]
    MEAL_PREFERENCES_CHOICES = [
        ('Végétalien(ne)', 'Végétalien(ne)'),
        ('Végétarien(ne)', 'Végétarien(ne)'),
        ('Sans gluten', 'Sans gluten'),
        ('Aucune préférence particulière', 'Aucune préférence particulière'),
    ]
    SOCIAL_INTERACTIONS_CHOICES = [
        ('Interactions sociales fréquentes', 'Interactions sociales fréquentes'),
        ('Préférence pour lindépendance', 'Préférence pour lindépendance'),
    ]
    LIFESTYLE_CHOICES = [
        ('Du matin', 'Du matin'),
        ('Du soir', 'Du soir'),
    ]
    COMMON_AREA_USAGE_CHOICES = [
        ('Utilisation fréquente des espaces communs', 'Utilisation fréquente des espaces communs'),
        ('Utilisation occasionnelle des espaces communs', 'Utilisation occasionnelle des espaces communs'),
    ]
    ACADEMIC_INTERESTS_CHOICES = [
        ('Sciences', 'Sciences'),
        ('Arts', 'Arts'),
        ('Affaires', 'Affaires'),
        ('Autre', 'Autre'),
    ]
    CONSUMPTION_HABITS_CHOICES = [
        ('Fumeur(se)', 'Fumeur(se)'),
        ('Non fumeur(se)', 'Non fumeur(se)'),
        ('Buveur(se) occasionnel(le)', 'Buveur(se) occasionnel(le)'),
        ('Non buveur(se)', 'Non buveur(se)'),
    ]
    PET_FRIENDLINESS_CHOICES = [
        ('Allergique aux animaux', 'Allergique aux animaux'),
        ('Amateur(trice) danimaux de compagnie', 'Amateur(trice) danimaux de compagnie'),
        ('Neutre', 'Neutre'),
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
