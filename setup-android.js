const fs = require("fs");
const javaDir = "android/app/src/main/java/com/masomenos/juego";
for (const f of ["WallpaperPlugin.java", "MainActivity.java"]) fs.copyFileSync(f, javaDir + "/" + f);
const mf = "android/app/src/main/AndroidManifest.xml";
let x = fs.readFileSync(mf, "utf8");
if (!x.includes("SET_WALLPAPER"))
  x = x.replace("<application", '<uses-permission android:name="android.permission.SET_WALLPAPER" />\n\n    <application');
fs.writeFileSync(mf, x);
console.log("Plugin instalado");
