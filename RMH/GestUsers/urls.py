from django.urls import path, include
from rest_framework.routers import SimpleRouter, DefaultRouter
from django.contrib.auth import views as auth_views
from rest_framework_simplejwt.views import TokenObtainPairView, TokenRefreshView

from . import views
from .views import *

router = DefaultRouter()
router.register(r'user', UserViewSet, basename='user')
router.register(r'profile', ProfileViewSet)

urlpatterns = [path('user/login/', UserViewSet.as_view({'post': 'login'}), name='login'),
               path('user/logout/', UserViewSet.as_view({'post': 'logout'}), name='logout'),
               path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
               path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
               path('api/token/refresh/custom/', RefreshTokenView.as_view(), name='token_refresh_custom'),

               ]
urlpatterns += router.urls
