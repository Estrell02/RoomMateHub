from django.urls import path
from rest_framework.routers import DefaultRouter
from .views import *

from . import views
router = DefaultRouter()
router.register(r'housing', HousingViewSet)
urlpatterns = [

]
urlpatterns += router.urls