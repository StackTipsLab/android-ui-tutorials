package com.stacktips.example;

import android.content.Intent;
import android.service.quicksettings.TileService;

/**
 * Created by Nilanchala Panigrahy on 8/10/16.
 */
public class MyAppTileService extends TileService {
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * Called when the tile is added to the quick settings from the edit interface by the user. If
     * you keep track of added tiles, override this and update it.
     * <p>
     * Return either TILE_MODE_ACTIVE or TILE_MODE_PASSIVE depending on your requirements
     */
    @Override
    public void onTileAdded() {
        super.onTileAdded();
    }

    /**
     * Called when the tile is removed from the quick settings using the edit interface. Similarly
     * to onTileAdded, override this and update your tracking here if you need to
     */
    @Override
    public void onTileRemoved() {
        super.onTileRemoved();
    }

    /**
     * Called when the tile is brought into the listening state. Update it with your icon and title
     * here, using getQsTile to get the tile (see below)
     */
    @Override
    public void onStartListening() {
        super.onStartListening();
    }

    /**
     * Called when the tile is brought out of the listening state. This represents when getQsTile
     * will now return null.
     */
    @Override
    public void onStopListening() {
        super.onStopListening();
    }

    /**
     * Called when the tile is clicked. Can be called multiple times in short succession, so double
     * click (and beyond) is possible
     */
    @Override
    public void onClick() {
        super.onClick();

        //Start main activity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
