package usbong.android.features.node;

import usbong.android.sunshine.alpha.UsbongDecisionTreeEngineActivity;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;

/*
 * Reference: answer by Seshu Vinay at stackoverflow
 * http://stackoverflow.com/questions/8831050/android-how-to-read-qr-code-in-my-application;
 * last accessed: Dec. 23, 2012
 */

public class QRCodeReaderActivity extends Activity
{
	public String myQRCodeReaderName;
/*
	private String currentWord;
	private String timeStamp;
*/
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		// Hide the window title.
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        try {
            Intent intent = new Intent(
                    "com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, 0);
        } catch (Exception e) {

            Uri marketUri = Uri
                    .parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW,
                    marketUri);
            startActivity(marketIntent);
        }
        
        myQRCodeReaderName=this.getIntent().getStringExtra("myQRCodeReaderName");
/*
        new AlertDialog.Builder(QRCodeReaderActivity.this).setTitle("Usbong Tip")
//			.setMessage(currentWord)
        	.setMessage("Touch the screen with your stylus or finger and start drawing! When you're done, hit the menu button and save your work.")
			.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
			}
		}).show();		
*/		
	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String content = data.getStringExtra("SCAN_RESULT");
                
                //added by Mike, Dec. 23, 2012
                UsbongDecisionTreeEngineActivity.setQRCodeContent(content);
			    //removed by Mike, Sept. 21, 2013
//				UsbongDecisionTreeEngineActivity.setCurrScreen(UsbongDecisionTreeEngineActivity.QR_CODE_READER_SCREEN);				
				this.finish();

			}
			if(resultCode == RESULT_CANCELED){
				//handle cancel
				this.finish();
			}
        }
	}
}