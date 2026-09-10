import java.util.*;

class ThroneInheritance {

    // parent -> list of children in birth order
    private Map<String, List<String>> children;

    // person -> whether they are dead
    private Set<String> dead;

    private String king;

    public ThroneInheritance(String kingName) {
        king = kingName;
        children = new HashMap<>();
        dead = new HashSet<>();

        children.put(kingName, new ArrayList<>());
    }

    public void birth(String parentName, String childName) {
        children.putIfAbsent(childName, new ArrayList<>());
        children.get(parentName).add(childName);
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> order = new ArrayList<>();

        dfs(king, order);

        return order;
    }

    private void dfs(String person, List<String> order) {

        // Add person if alive
        if (!dead.contains(person)) {
            order.add(person);
        }

        // Visit children in birth order
        for (String child : children.get(person)) {
            dfs(child, order);
        }
    }
}
