package mino.com.sttapp.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jorgesanmartin on 2/25/16.
 */
public class AppAssetsManager {
    public static String getAssetFromFileName(Context context, String filename) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray);
    }
}
