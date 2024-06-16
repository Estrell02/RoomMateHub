from django.urls import path, include
from rest_framework.routers import SimpleRouter, DefaultRouter
from rest_framework_simplejwt.views import TokenRefreshView
from django.conf.urls.static import static
from django.conf import settings


from .views import *

router = DefaultRouter()
router.register(r'user', UserViewSet, basename='user')
router.register(r'profile', ProfileViewSet)

urlpatterns = [path('user/login/', UserViewSet.as_view({'post': 'login'}), name='login'),
               path('user/logout/', UserViewSet.as_view({'post': 'logout'}), name='logout'),
               path('profile/<int:pk>/find_matching_profiles/', ProfileViewSet.as_view({'get': 'find_matching_profiles'}), name='find-matching-profiles'),
               path('api/token/', TokenObtainPairView.as_view(), name='token_obtain_pair'),
               path('api/token/refresh/', TokenRefreshView.as_view(), name='token_refresh'),
               path('api/token/refresh/custom/', RefreshTokenView.as_view(), name='token_refresh_custom'),
               path('', include(router.urls)),
               ]
urlpatterns += router.urls
