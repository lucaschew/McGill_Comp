import json
import argparse
import networkx as nx

def compute_network_stats(input_path, output_path):
    # Load the interaction network
    with open(input_path, 'r', encoding='utf-8') as f:
        interaction_network = json.load(f)
    
    # Create a graph
    G = nx.Graph()
    
    # Add edges with weights
    for char1, neighbors in interaction_network.items():
        for char2, weight in neighbors.items():
            G.add_edge(char1, char2, weight=weight)
    
    # Compute degree centrality
    degree_centrality = nx.degree_centrality(G)
    top_degree = sorted(degree_centrality, key=degree_centrality.get, reverse=True)[:3]
    
    # Compute weighted degree centrality
    weighted_degree = {node: sum(data['weight'] for _, _, data in G.edges(node, data=True)) for node in G.nodes()}
    top_weighted_degree = sorted(weighted_degree, key=weighted_degree.get, reverse=True)[:3]
    
    # Compute closeness centrality
    closeness_centrality = nx.closeness_centrality(G)
    top_closeness = sorted(closeness_centrality, key=closeness_centrality.get, reverse=True)[:3]
    
    # Compute betweenness centrality
    betweenness_centrality = nx.betweenness_centrality(G, weight='weight')
    top_betweenness = sorted(betweenness_centrality, key=betweenness_centrality.get, reverse=True)[:3]
    
    # Save results
    stats = {
        "degree": top_degree,
        "weighted_degree": top_weighted_degree,
        "closeness": top_closeness,
        "betweenness": top_betweenness
    }
    
    with open(output_path, 'w', encoding='utf-8') as f:
        json.dump(stats, f, indent=4)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Compute network stats from interaction JSON.")
    parser.add_argument("-i", "--input", required=True, help="Path to input JSON file.")
    parser.add_argument("-o", "--output", required=True, help="Path to output JSON file.")
    args = parser.parse_args()

    compute_network_stats(args.input, args.output)
