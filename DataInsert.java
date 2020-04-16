
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DataInsert {
    public static void main(String[] args) throws UnknownHostException {

        Settings settings =  Settings.builder()
                .put("cluster.name","elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));


        Map<String,Object> Json= new HashMap<>();
        Json.put("name","Asus Vivobook x542Tr");
        Json.put("detail","Intel Core i5,8G Ram,1TB HDisk");
        Json.put("price","3500");
        Json.put("provider","Asus TR");



        IndexResponse indexResponse =client.prepareIndex("product","doc","3")  // index type (db,table)
                .setSource(Json, XContentType.JSON)
                .get();

        System.out.println(indexResponse.getId());







        /*
        List<DiscoveryNode> discoveryNodes =client.listedNodes();

        for(int i=0;i<discoveryNodes.size();i++)
        {

            System.out.println(discoveryNodes.get(i));

        }

*/


    }
}

