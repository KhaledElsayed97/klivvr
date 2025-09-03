package dev.khaled.klivvr.data.structure

import dev.khaled.klivvr.domain.model.CityDomainModel

/**
 *
 *
A Trie data structure is beneficial here because it allows fast prefix lookups in O(length of prefix) time,
instead of scanning every city name, we can just walk down the characters of the query (i.e a -> l -> b) until we reach the matching node,
and from there we can instantly get all the cities under this node/prefix.

Which is much faster than scanning or filtering a large list (200k entries) on each keystroke since the requirement is to keep updating while typing
, this keeps the UI responsive and fast as the user types.
 */

data class TrieNode(
    val children: MutableMap<Char, TrieNode> = mutableMapOf(),
    val cities: MutableList<CityDomainModel> = mutableListOf()
)

class CityTrie {
    private val root = TrieNode()

    fun insert(city: CityDomainModel) {
        var current = root
        val name = city.name.lowercase()

        for (i in name.indices) {
            val char = name[i]
            if (!current.children.containsKey(char)) {
                current.children[char] = TrieNode()
            }
            current = current.children[char]!!
            current.cities.add(city)
        }
    }

    fun search(prefix: String): List<CityDomainModel> {
        var current = root

        for (char in prefix.lowercase()) {
            if (!current.children.containsKey(char)) {
                return emptyList()
            }
            current = current.children[char]!!
        }

        return current.cities.toList()
    }

    fun getAllCities(): List<CityDomainModel> {
        val allCities = mutableSetOf<CityDomainModel>()
        collectAllCities(root, allCities)
        return allCities.toList()
    }

    private fun collectAllCities(node: TrieNode, cities: MutableSet<CityDomainModel>) {
        cities.addAll(node.cities)
        node.children.values.forEach { child ->
            collectAllCities(child, cities)
        }
    }

    fun clear() {
        root.children.clear()
        root.cities.clear()
    }
}

