package com.masomenos.juego;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@CapacitorPlugin(name = "Wallpaper")
public class WallpaperPlugin extends Plugin {
    @PluginMethod
    public void setFromUrl(PluginCall call) {
        String url = call.getString("url");
        if (url == null) { call.reject("Falta la URL"); return; }
        new Thread(() -> {
            try {
                HttpURLConnection c = (HttpURLConnection) new URL(url).openConnection();
                c.setRequestProperty("User-Agent", "MasOMenos/1.0 (juego personal)");
                InputStream in = c.getInputStream();
                Bitmap b = BitmapFactory.decodeStream(in);
                in.close();
                if (b == null) { call.reject("Imagen no válida"); return; }
                WallpaperManager.getInstance(getContext()).setBitmap(b);
                call.resolve();
            } catch (Exception e) {
                call.reject(e.getMessage());
            }
        }).start();
    }
}
