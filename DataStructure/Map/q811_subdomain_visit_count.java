package DataStructure.Map;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class q811_subdomain_visit_count {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String cp: cpdomains) {
            String[] cnt_domains = cp.split(" ");
            int cnt = Integer.parseInt(cnt_domains[0]);
            String domains = cnt_domains[1];
            map.put(domains, map.getOrDefault(domains, 0) + cnt);
            // 注意：对子域名的处理（找到'.'处，进行字符串分割substring）
//            String[] subDomains = domains.split("\\.");
            for (int i = 0; i < domains.length(); i++) {
                if (domains.charAt(i) == '.') {
                    String subDomain = domains.substring(i+1);
                    map.put(subDomain, map.getOrDefault(subDomain, 0) + cnt);
                }
            }
        }

        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            res.add(entry.getValue() + " " + entry.getKey());
        }
        return res;
    }
}
