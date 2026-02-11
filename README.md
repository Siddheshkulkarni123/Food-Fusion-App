🍲 Food Fusion App

Food Fusion is a native Android application developed using Java and Android Studio.  
The application enables users to securely authenticate, upload food recipes with images, and browse detailed recipe information in real time using Firebase services.

---

📱 Features

- 🔐 Secure User Authentication (Firebase Authentication)
- 📝 Upload and Manage Recipes
- 🖼 Image Upload and Storage (Firebase Cloud Storage)
- 📂 Real-Time Recipe Data Storage (Firebase Realtime Database)
- 📄 Detailed Recipe View
- 🔄 Live Data Synchronization Across Devices

---

🛠 Technology Stack

- Language: Java  
- IDE: Android Studio  
- Backend Services: Firebase  
  - Firebase Authentication  
  - Firebase Realtime Database  
  - Firebase Cloud Storage  
- UI Components: XML Layouts, RecyclerView  
- Build System: Gradle (Kotlin DSL)

---

🏗 Project Architecture Overview

The application follows a structured Activity-based architecture:

- MainActivity – Application entry point  
- Signup / Signin – Firebase-based user authentication  
- Home – Displays list of recipes  
- Upload_recipe – Allows users to upload new recipes with images  
- DetailActivity – Displays complete recipe details  
- FoodData – Model class for recipe data  
- MyAdapter – RecyclerView adapter for displaying recipes  

---
 🔥 Firebase Implementation

 Firebase Authentication
- Email & Password-based login and registration

Firebase Realtime Database
- Stores recipe metadata including:
  - Title
  - Description
  - Additional details
  - Image URL reference
- Provides real-time data synchronization

Firebase Cloud Storage
- Stores uploaded recipe images
- Generates secure download URLs
- Linked with corresponding database records


