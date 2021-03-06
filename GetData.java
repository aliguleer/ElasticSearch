
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;


public class GetData {
    public static void main(String[] args) throws UnknownHostException {

        Settings settings =  Settings.builder()
                .put("cluster.name","elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));

/*
        Map<String,Object> Json= new HashMap<>();
        Json.put("name","Apple Macbook Air");
        Json.put("detail","Intel Core i,16Gb Ram,500 GB HDisk");
        Json.put("price","5600");
        Json.put("provider","Apple TR");



        IndexResponse indexResponse =client.prepareIndex("product","doc","1")  // index type (db,table)
                .setSource(Json, XContentType.JSON)
                .get();

        System.out.println(indexResponse.getId());

        */


/*
        GetResponse response =client.prepareGet("product","doc","3").get();
        Map<String,Object> source =response.getSource();
        String name = (String) source.get("name");
        String price = (String) source.get("price");
        String detail = (String) source.get("detail");
        String provider = (String) source.get("provider");

        System.out.println("name = "+name);
        System.out.println("detail = "+detail);
        System.out.println("price = "+price);
        System.out.println("provider = "+provider);
*/



        SearchResponse response = client.prepareSearch("product")
                .setTypes("doc")
                .setQuery(QueryBuilders.matchQuery("provider","TR"))
                .get();

         SearchHit[] hits = response.getHits().getHits();

         for(SearchHit hit : hits)
         {
             Map<String,Object> sourceAsMap= hit.getSourceAsMap();
             System.out.println(sourceAsMap);
         }




        /*
        List<DiscoveryNode> discoveryNodes =client.listedNodes();

        for(int i=0;i<discoveryNodes.size();i++)
        {

            System.out.println(discoveryNodes.get(i));

        }

*/


    }
}

