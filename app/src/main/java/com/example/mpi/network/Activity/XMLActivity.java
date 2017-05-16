package com.example.mpi.network.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.mpi.network.R;
import com.example.mpi.network.util.MyToast;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class XMLActivity extends AppCompatActivity {
    private TextView xmlText;
    private TextView xmlText2;
    private final String URI = "http://139.199.71.40/get_data.xml";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xml);

        xmlText=(TextView)findViewById(R.id.xmlTextView);
        xmlText2=(TextView)findViewById(R.id.xmlTextView2);
        Button button=(Button)findViewById(R.id.pullXML);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showToast(XMLActivity.this,"网络请求已发送");
                xmlText2.setText("");
                sendRequestWithOkHttpPull();
            }
        });

        Button button1=(Button)findViewById(R.id.saxXML);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyToast.showToast(XMLActivity.this,"网络请求已发送");
                xmlText2.setText("");
                sendRequestWithOkHttpSAX();
            }
        });
    }

    void sendRequestWithOkHttpSAX()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                        Request request=new Request.Builder().url(URI).build();
                        try {
                            Response response=client.newCall(request).execute();
                            String responseData=response.body().string();
                    parseXMLWithSAX(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    void sendRequestWithOkHttpPull()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client=new OkHttpClient();
                Request request=new Request.Builder().url(URI).build();
                try {
                    Response response=client.newCall(request).execute();
                    String responseData=response.body().string();
                    showRes(responseData);
                    parseXMLWithPull(responseData);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void parseXMLWithSAX(String xmlData)
    {
        SAXParserFactory factory=SAXParserFactory.newInstance();
        try {
            XMLReader xmlReader=factory.newSAXParser().getXMLReader();
            ContentHandler handler=new ContentHandler();
            xmlReader.setContentHandler(handler);
            xmlReader.parse(new InputSource(new StringReader(xmlData)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    void parseXMLWithPull(String xmlData)
    {
        try {
            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser=factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlData));
            int eventType=xmlPullParser.getEventType();
            String ip="";
            String name="";
            String version="";
            while(eventType!=XmlPullParser.END_DOCUMENT)
            {
                String nodeName=xmlPullParser.getName();
                switch (eventType)
                {
                    case XmlPullParser.START_TAG:
                    {
                        if("ip".equals(nodeName))
                        {
                            ip=xmlPullParser.nextText();
                        }
                        else if("name".equals(nodeName))
                        {
                            name=xmlPullParser.nextText();
                        }
                        else if("version".equals(nodeName))
                        {
                            version=xmlPullParser.nextText();
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG:
                    {
                        if("app".equals(nodeName))
                        {
                            showXMLData("ip:"+ip+" name:"+name+" version:"+version+"\n");
                        }
                    }break;
                    default:break;
                }
                eventType=xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void showXMLData(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                String s=xmlText2.getText().toString()+response;
                xmlText2.setText(s);
            }
        });
    }
    void showRes(final String response)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run()
            {
                String s=response;
                xmlText.setText(s);
            }
        });
    }

    class ContentHandler extends DefaultHandler
    {
        String nodeName;
        StringBuilder ip;
        StringBuilder name;
        StringBuilder version;

    @Override
        public void startDocument() throws SAXException
    {
            ip=new StringBuilder();
            name=new StringBuilder();
            version=new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            nodeName=localName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if("ip".equals(nodeName))
                ip.append(ch,start,length);
            else  if("name".equals(nodeName))
                name.append(ch,start,length);
            else if("version".equals(nodeName))
                version.append(ch,start,length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if("app".equals(localName))
            {
                showXMLData("ip:"+ip.toString().trim()+" name:"+name.toString().trim()+"version:"+version.toString().trim()+"\n");
                ip.setLength(0);
                name.setLength(0);
                version.setLength(0);
            }
        }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
}
