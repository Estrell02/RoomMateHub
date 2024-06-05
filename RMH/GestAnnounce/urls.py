from rest_framework.routers import DefaultRouter
from .views import *
from django.urls import path, include

router = DefaultRouter()
router.register(r'housing', HousingViewSet)
router.register(r'housing_application', HousingApplicationViewset)
urlpatterns = [
path('', include(router.urls)),
]
urlpatterns += router.urls
