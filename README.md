# OC_P3

## BDD

#### Lancer MAMP ou XAMP sur le port 3306
> ℹ️ Changer dans application.properties vos logs de connexion à votre BDD
```
curl http://localhost:8080/actuator/health/db
````

### Environnement

Dans src/main/resources créer un fichier application-dev.properties et rajouter vos logs de bdd

```
spring.datasource.username=#YOUR_USERNAME#
spring.datasource.password=#YOUR_PASSWORD#
```