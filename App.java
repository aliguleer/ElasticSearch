
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;


public class App {
    public static void main(String[] args) throws UnknownHostException {

        Settings settings =  Settings.builder()
                .put("cluster.name","elasticsearch").build();

        TransportClient client = new PreBuiltTransportClient(settings)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"),9300));

        List<DiscoveryNode> discoveryNodes =client.listedNodes();

        for(int i=0;i<discoveryNodes.size();i++)
        {

            System.out.println(discoveryNodes.get(i));

        }




    }
}

