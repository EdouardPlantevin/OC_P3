# 🚀 Guide de démarrage – Application Spring Boot

## ✅ Prérequis

* **Java** 21
* **Maven** 3.9+
* **MySQL** via [MAMP](https://www.mamp.info/) (macOS) ou [XAMPP](https://www.apachefriends.org/) (Windows/macOS/Linux)
* Un client MySQL :

    * Terminal / CLI `mysql`
    * ou **phpMyAdmin**

---

## 1️⃣ Démarrer MySQL

### ▶️ Avec **MAMP**

1. Lancez **MAMP**.
2. Démarrez les serveurs.
3. **Ports par défaut :**

    * MySQL : `8889` (modifiable dans *Préférences > Ports*)
    * phpMyAdmin : [http://localhost:8888/phpMyAdmin](http://localhost:8888/phpMyAdmin)

### ▶️ Avec **XAMPP**

1. Lancez le **Control Panel**.
2. Démarrez **MySQL**.
3. **Ports par défaut :**

    * MySQL : `3306`
    * phpMyAdmin : [http://localhost/phpmyadmin](http://localhost/phpmyadmin)

---

## 2️⃣ Configuration `application.properties`

Dans **`src/main/resources/application.properties`** :
➡️ Remplacez la valeur de `spring.datasource.url` (ligne 9) par l’URL de votre base MySQL.

---

## 3️⃣ Créer le fichier `application-dev.properties`

📂 Emplacement : `src/main/resources/application-dev.properties`

```properties
spring.datasource.username=
spring.datasource.password=

# JWT - Générez une clé en Base64 : openssl rand -base64 64
jwt.base64-secret=
```

---

## 4️⃣ Activer le profil `dev`

Le profil `dev` est activé **par défaut** via le `pom.xml`.
Si besoin, vous pouvez le forcer :

* **En ligne de commande :**

  ```bash
  mvn spring-boot:run -Dspring-boot.run.profiles=dev
  ```

---

## 5️⃣ Lancer l’application


```bash
 mvn spring-boot:run
```

---

## 6️⃣ Vérifications

* ❤️ Santé : [http://localhost:3001/actuator/health](http://localhost:3001/actuator/health)
* 📖 Documentation Swagger UI : [http://localhost:3001/swagger-ui/index.html](http://localhost:3001/swagger-ui/index.html)

---

## (Optionnel)

Une fois l'application lancer pour la première fois et le shema de la BDD créé,
un script SQL est disponible dans **`sql/script.sql`**.  
Il contient :
- **3 annonces**  
- **1 utilisateur** avec les identifiants suivants :

email: test@test.com  
password: password

## 7️⃣ Dépannage

* ❌ **Access denied for user**
  → Vérifiez `spring.datasource.username` / `spring.datasource.password` et les droits sur la base.

* ❌ **Connection refused**
  → Vérifiez que MySQL est démarré et que le port est correct (`8889` MAMP, `3306` XAMPP).

* ❌ **Erreur JWT**
  → Assurez-vous que `jwt.base64-secret` est une **chaîne Base64 valide et non vide**. (openssl rand -base64 64)

---

## 🎉 Résultat

Une fois la base MySQL démarrée et le fichier **`application-dev.properties`** configuré, l’application :

* démarre sur le port **3001**
* initialise automatiquement le schéma en base

---
