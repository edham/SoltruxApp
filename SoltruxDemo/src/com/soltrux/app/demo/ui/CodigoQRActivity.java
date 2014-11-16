/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.soltrux.app.demo.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Toditos
 */
public class CodigoQRActivity extends Activity {

    /**
     * Called when the activity is first created.
     */
     private Spinner ComboCodigo;
    private String[] listCodigo;
    private Bitmap bitmap = null;
    
   private EditText txtCodigo;
        
        private View ImagenView;
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.codigo_qr);
        ImagenView = (View) findViewById(R.id.ImagenView);
        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        ComboCodigo = (Spinner)findViewById(R.id.ComboCodigo);
        // ToDo add your GUI initialization code here        
        TextView lblLink = (TextView)findViewById(R.id.lblLink);
        lblLink.setText(Html.fromHtml(getString(R.string.web)));
        Linkify.addLinks(lblLink, Linkify.ALL);
        lblLink.setMovementMethod(LinkMovementMethod.getInstance());
        listCodigo=getResources().getStringArray(R.array.array_codigo);
        ArrayAdapter<String> adapterSexo = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,listCodigo);       
        adapterSexo.setDropDownViewResource(android.R.layout.simple_spinner_item);
        ComboCodigo.setAdapter(adapterSexo); 
         ComboCodigo.setSelection(0);
    }
    
      public void btnShare(View v)
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND); 
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, getString(R.string.str_compartir_sms));
        startActivity(Intent.createChooser(sharingIntent, getString(R.string.str_compartir_via)));
        startActivity(Intent.createChooser(sharingIntent,getString(R.string.str_compartir_con)));
        
    }
      
       public void btnGenerar(View v)
  {
      if(!txtCodigo.getText().toString().equals("") &&  !txtCodigo.getText().toString().equals(null))
        try {
            if(ComboCodigo.getSelectedItemPosition()==0)
            bitmap=generateQR(txtCodigo.getText().toString(),200,200);
            else
                bitmap=generateBarras(txtCodigo.getText().toString(),200,200);
            Drawable d = new BitmapDrawable(getResources(),bitmap);
            ImagenView.setBackgroundDrawable(d);
            txtCodigo.setText("");
            
                   
        } catch (Exception ex) {
            Logger.getLogger(LoginActivity.class.getName()).log(Level.SEVERE, null, ex);
        }
      else
          Toast.makeText(this,getString(R.string.str_escriba_texto), Toast.LENGTH_LONG).show();
  }
       
       
        public Bitmap generateQR(String text, int h, int w) throws Exception {
 
        Charset charset = Charset.forName("ISO-8859-1");
        CharsetEncoder encoder = charset.newEncoder();
        byte[] b = null;
        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(text));
        b = bbuf.array();
        String data = new String(b, "ISO-8859-1");
        // get a byte matrix for the data
        
        
        BitMatrix matrix = null;
        QRCodeWriter writer = new QRCodeWriter();
        matrix = writer.encode(data, com.google.zxing.BarcodeFormat.QR_CODE, w, h);

        
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                    bmp.setPixel(x, y, matrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
            }
    
        return bmp;

    }
       
       
       public Bitmap generateBarras(String text, int h, int w) throws Exception {
 
        Charset charset = Charset.forName("ISO-8859-1");
        CharsetEncoder encoder = charset.newEncoder();
        byte[] b = null;
        ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(text));
        b = bbuf.array();
        String data = new String(b, "ISO-8859-1");
        // get a byte matrix for the data
        
        
        BitMatrix matrix = null;
        
        matrix = new Code128Writer().encode(data, BarcodeFormat.CODE_128, w, h, null);

        
        int height = matrix.getHeight();
        int width = matrix.getWidth();
        Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++){
                for (int y = 0; y < height; y++){
                    bmp.setPixel(x, y, matrix.get(x,y) ? Color.BLACK : Color.WHITE);
                }
            }
    
        return bmp;

    }
}
