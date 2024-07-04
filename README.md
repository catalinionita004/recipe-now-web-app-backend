# 🍽️ Recipe Now Web App Backend

Welcome to the backend repository for the Recipe Now Web App! Check out our frontend application live at the link below:

# 🌟 **Live Demo** 🌟

[![Recipe Now](https://img.shields.io/badge/Recipe%20Now-Live%20Demo-brightgreen?style=for-the-badge)](https://recipe-now-frontend-s7qlbu7unq-ez.a.run.app/)

## ⏳ Startup Time & Demo Video

🚀 **Note:** Due to the use of free trials for cloud services, it might take approximately **1 minute** for the application to start. I appreciate your patience! 🙏

🎬 In the meantime, why not watch our demo video to see the application in action? Check it out below! 🍿

# RecipeNow app - Recipe Recommendation System 

## 🍽️ Overview
Welcome to the RecipeNow repository! This project implements a user-based collaborative filtering algorithm for recommending recipes to users based on their preferences. The system also includes a web interface for users to interact with the recommendation system.

## ✨ Key Features

- 🧑‍🤝‍🧑 **User-based Collaborative Filtering Algorithm** for personalized recipe recommendations.
- 🌐 **Web Interface** for easy interaction with the recommendation system.
- 📊 **Data Collection, Cleaning, and Exploratory Data Analysis** processes.
- 💻 **Source Code** for the recommendation system and web interface.

## 🌟 Highlights

- ❄️ **Addresses Cold-Start Issues** in recommendation systems.
- 📈 **Implementation of Recommendation Algorithms** for personalized suggestions.
- 🍳 **Explores the Growing Interest in Culinary Arts** and online cooking platforms.
- 🧠 **Development of the User-Based Collaborative Filtering Model**.
- 🎨 **Design and Implementation of the Web Interface** for recipe recommendations.
- 🚀 **Suggestions for Enhancing System Performance** and expanding application areas.
- 
## 🛠️ Technologies Used

### 🖥️ Programming Language
- ![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)

### 📚 Libraries/Frameworks
- ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
- ![Liquibase](https://img.shields.io/badge/Liquibase-006CD3?style=for-the-badge&logo=liquibase&logoColor=white)
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white)
- ![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jwt&logoColor=white)

### ⚙️ Additional Tools
- **Liquibase** for database migrations and version control.
- **PostgreSQL** as the primary database management system.
- **JWT (JSON Web Tokens)** for securing API endpoints and authentication.

These technologies and tools were chosen to ensure a robust, scalable, and secure application. The combination of Java and Spring provides a strong backend framework. Liquibase and PostgreSQL together offer reliable data management solutions, and JWT tokens enhance the security of the application.


### 📊 Data Analysis
- **Data collection, cleaning, exploratory data analysis**

### 🔍 Recommendation System
- **User-based collaborative filtering algorithm**

## 📊 Data Processing

We utilized the [Food.com Recipes and User Interactions](https://www.kaggle.com/datasets/shuyangli94/food-com-recipes-and-user-interactions?resource=download) dataset, which includes:

- 📋 **230,000+ recipes**
- 🧑🏻 **240,000+** users**
- 👥 **1,000,000+ user interactions**

### Steps in Data Processing:
1. 📥 **Data Collection:** Imported the dataset from Kaggle, ensuring comprehensive coverage of recipes and user interactions.
2. 🧹 **Data Cleaning:** Removed duplicates, handled missing values, and standardized data formats for consistency.
3. 🔍 **Exploratory Data Analysis (EDA):** Analyzed the dataset to extract meaningful insights and understand user preferences and trends.
4. 🛠️ **Feature Engineering:** Created new features to improve the performance of the recommendation system.
5. 🧠 **Model Training:** Implemented and trained a user-based collaborative filtering algorithm to recommend personalized recipes.

## ☁️ Cloud Hosting

Our application is hosted in the cloud to ensure scalability and reliability:

### Backend and Frontend
- ☁️ **Google Cloud Run:** Utilized Google Cloud Run to host both the backend and frontend of the application. Cloud Run allows us to deploy containerized applications that scale automatically.
  
### Database
- 🗄️ **AWS (Amazon Web Services):** The database is hosted on AWS, utilizing the free trial. This setup ensures robust data storage and management capabilities.

## Demo Video
[![IMAGE ALT TEXT HERE](https://camo.githubusercontent.com/414ef4e0ce20d5c28416c3d6419611ca27ebc3e4fd85895054fa129c1f2637c8/68747470733a2f2f692e626c6f67732e65732f3962313961642f796f75747562652f3435305f313030302e77656270)](https://www.youtube.com/watch?v=3RRp6jHgWQ4)


Steps to run locally
1. git pull https://github.com/catalinionita004/recipe-now-web-app-backend.git
2. create locally a database with name 'recipe-now'
3. populate the database
