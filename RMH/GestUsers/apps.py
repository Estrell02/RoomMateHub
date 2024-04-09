from django.apps import AppConfig


class GestusersConfig(AppConfig):
    default_auto_field = 'django.db.models.BigAutoField'
    name = 'GestUsers'

    def ready(self):
        from GestUsers import signals
