from django.urls import path, include
from rest_framework.routers import SimpleRouter, DefaultRouter
from django.contrib.auth import views as auth_views

from . import views
from .views import *

router = DefaultRouter()
router.register(r'user', UserViewSet)
router.register(r'profile', ProfileViewSet)


urlpatterns = [ ]
urlpatterns += router.urls