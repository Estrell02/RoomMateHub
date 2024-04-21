from django.urls import path, include
from rest_framework.routers import SimpleRouter, DefaultRouter
from django.contrib.auth import views as auth_views

from . import views
from .views import *

router = DefaultRouter()
router.register(r'user', UserViewSet)
router.register(r'profile', ProfileViewSet)


urlpatterns = [  path('users/login/', UserViewSet.as_view({'post': 'login'}), name='user_login'),
    path('users/logout/', UserViewSet.as_view({'post': 'logout'}), name='user_logout')
                 ]
urlpatterns += router.urls