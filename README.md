# GetSetGo

This is a very simple demo project. I have tried to merge most import elements which are related to Professional Android Application Development.

##### Application Overview:
A user can easily Signup to get an account by inputting informations. If user already have an account he/she can easily login by input credentials. Home activity consists of large number of places information. By pressing one item user will see the full details of that place. **Google Map** also added to show the exact location of that place.


##### Application Frontend:
This is a demo app. That’s why I didn’t put that much importance on it. I know good looking is also very important. Day by day it will improve.

##### Application Backend:
- **Database:** I have used **MySQL Database**. For now, there are two tables. First one is “**users**”, where user information stored. Now it is having 4 columns (id, name, email, password). Second one is “**places**”, where place information stored. Now that table is having 6 columns (id, name, location, description, latitude, longitude). All data’s are fetching from this database.
- **REST API:** To maintain **HTTP** calls, **REST API** has been created. To create **REST API** I have used [Slim Framework](http://www.slimframework.com). For now I have 3 Calls(2 POST, 1 GET). POST calls are “**createuser**” and “**userlogin**”. GET call is “**places**”. I have already made a [Repository](https://github.com/mahdiandrovo/GetSetGo-API) for this.
- **Retrofit:** To maintain HTTP call in application [Retrofit](https://square.github.io/retrofit/) has been used.
- **MVVM:** To keep clean coding in the project **Model View View Model** architecture has been used. Each and every part maintained for every activity in the app.
- **Android Jetpack Components (Architecture):** To avoid writing boilerplate code [Android Jetpack Component (Architecture)](https://developer.android.com/jetpack/?gclid=EAIaIQobChMIqoWGr_OL6QIV2I2PCh019AsgEAAYASAAEgJ2VPD_BwE&gclsrc=aw.ds) helped a lot. Mainly I have used **DataBinding**, **Livedata**, **Room Database**, **ViewModel**. Those also helped a lot to maintain the MVVM structure.
- **Google Map:** For now, **Google Map** is showing the particular place. The needed latitude and longitude are coming from the database.
##### Demo Video:
There is a [**Demo Video**](https://www.dropbox.com/sh/luiw11b1r4ezlio/AADUkT00m4b2yz8BiSVmSz5ea?dl=0) uploaded. You can easily check it.
