package org.andnav.osm.samples;

import org.andnav.osm.tileprovider.OpenStreetMapTileProviderDirect;
import org.andnav.osm.tileprovider.tilesource.TileSourceFactory;
import org.andnav.osm.tileprovider.util.CloudmadeUtil;
import org.andnav.osm.util.GeoPoint;
import org.andnav.osm.views.OpenStreetMapView;
import org.andnav.osm.views.overlay.OpenStreetMapTilesOverlay;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

/**
 * 
 * @author Alex van der Linden
 * 
 */
public class SampleWithTilesOverlay extends Activity {

	// ===========================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================

	private OpenStreetMapView mOsmv;
	private OpenStreetMapTilesOverlay mTilesOverlay;
	private OpenStreetMapTileProviderDirect mProvider;
	private OpenStreetMapTileProviderDirect mTileProvider;

	// ===========================================================
	// Constructors
	// ===========================================================
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Setup base map
		CloudmadeUtil.retrieveCloudmadeKey(getApplicationContext()OpenStreetMapRendererFactory.setCloudmadeKey(cloudmadeKey);
		mTileProvider = new OpenStreetMapTileProviderDirecLayoutParams.FILL_PARENT,
				;

		this.mOsmv = new OpenStreetMapView(this, 256, mTileProvider);
		rl.addView(this.mOsmv, new RelativeLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		this.mOsmv.setBuiltInZoomControls(true);

		// zoom to the nemProvider = new OpenStreetMapTileProviderDirect(getApplicationContext()e
			pProvider.setTileSource(TileSourceoadcastReceiver aReceiver) {
			}
		};
		mProvider = new OpenStreetMapTilePro(this.mOsmv, mProvider,
				ver);
		mOsmv.setRenderer(OpenStreetMapRendererFactory.FIETS_OVERLAY_NL);
		this.mTilesOverlay = new OpenStreetMapTilesOverlay (this.mOsmv, mProvider, this.getBaseContext());
		this.mOsmv.getOverlays().add(this.mTilesOverlay);

		this.setContentView(rl);
	}

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods from SuperClass/Interfaces
	// ===========================================================

	// ===========================================================
	// Methods
	// ===========================================================

	// =========