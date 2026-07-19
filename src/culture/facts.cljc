(ns culture.facts
  "Country-level regional-culture catalog for Gabon (GAB) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"GAB"
   [{:culture/id "gab.dish.nyembwe-chicken"
     :culture/name "Nyembwe chicken"
     :culture/name-local "Poulet nyembwe"
     :culture/country "GAB"
     :culture/kind :dish
     :culture/summary "Chicken cooked in nyembwe, the Gabonese palm-butter sauce (from the Myene word for palm oil); considered a national dish of Gabon."
     :culture/url "https://en.wikipedia.org/wiki/Moambe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "gab.dish.fufu"
     :culture/name "Fufu"
     :culture/country "GAB"
     :culture/kind :dish
     :culture/summary "Pounded cassava, one of the traditional dishes of Gabonese cuisine, whose rural staples include cassava, rice and yams."
     :culture/url "https://en.wikipedia.org/wiki/Gabonese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "gab.dish.gari"
     :culture/name "Gari"
     :culture/country "GAB"
     :culture/kind :dish
     :culture/summary "Cassava flour prepared as a porridge, named among the traditional dishes of Gabonese cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Gabonese_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "gab.beverage.palm-wine"
     :culture/name "Palm wine (toutou)"
     :culture/country "GAB"
     :culture/kind :beverage
     :culture/summary "Alcoholic beverage made from palm sap and consumed across Africa; in Gabon it is known as toutou."
     :culture/url "https://en.wikipedia.org/wiki/Palm_wine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "gab.product.wild-mango"
     :culture/name "Wild mango (Irvingia gabonensis)"
     :culture/country "GAB"
     :culture/kind :product
     :culture/summary "African tree valued for its fat- and protein-rich nuts, often used in Gabonese cuisine to season poultry and meat and preserved as dika bread; the Gabonese government has prohibited logging of the tree until 2034."
     :culture/url "https://en.wikipedia.org/wiki/Irvingia_gabonensis"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "gab.heritage.lope"
     :culture/name "Lopé National Park"
     :culture/country "GAB"
     :culture/kind :heritage
     :culture/summary "National park in central Gabon designated a UNESCO World Heritage Site in 2007 as the 'Ecosystem and Relict Cultural Landscape of Lopé-Okanda', noted for its savanna-forest ecosystem and petroglyphs."
     :culture/url "https://en.wikipedia.org/wiki/Lop%C3%A9_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "gab.heritage.ivindo"
     :culture/name "Ivindo National Park"
     :culture/country "GAB"
     :culture/kind :heritage
     :culture/summary "3,000 km2 tropical-forest national park in east-central Gabon with spectacular waterfalls and forest elephants, designated a UNESCO World Heritage Site in 2021."
     :culture/url "https://en.wikipedia.org/wiki/Ivindo_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-gab culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "GAB"))
                 " GAB entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))
