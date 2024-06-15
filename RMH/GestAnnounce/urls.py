from rest_framework.routers import DefaultRouter

from GestUsers.views import UserViewSet
from .views import *
from django.urls import path, include

router = DefaultRouter()
router.register(r'housing', HousingViewSet)
router.register(r'housing_application', HousingApplicationViewset)
router.register(r'users', UserViewSet)
urlpatterns = [
path('', include(router.urls)),
]
urlpatterns += router.urls
