from django.db.models.signals import post_save, pre_delete
from django.dispatch import receiver
from GestUsers.models import Profile, User
from django.conf import settings
@receiver(post_save, sender=User)
def create_user_profile(sender, instance, created, **kwargs):
    if created:
        print("testreussi")
        profile=Profile.objects.create(user=instance, hobbies="",filiere="",vegan=False, picture="" )
        profile.save()
        print(Profile.objects.all())

@receiver(pre_delete, sender=User)
def delete_profile(sender, instance, **kwargs):
    profile = Profile.objects.get(user=instance)
    profile.delete()
