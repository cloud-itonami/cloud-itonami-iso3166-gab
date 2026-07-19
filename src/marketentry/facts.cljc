(ns marketentry.facts "Gabon market-entry catalog.")
(def catalog
  {"GAB" {:name "Gabon"
          :owner-authority "ARMP / e-procurement"
          :legal-basis "Code des marchés publics"
          :national-spec "e-procurement + RCCM/NIF"
          :provenance "https://www.armp.ga/"
          :required-evidence ["RCCM/NIF record" "e-procurement registration record" "RCCM extract" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / ARMP"
          :rep-legal-basis "Gabonese legal entity (RCCM) typically required for public awards"
          :rep-provenance "https://www.armp.ga/"
          :corporate-number-owner-authority "ANPI / DGI"
          :corporate-number-legal-basis "RCCM / NIF"
          :corporate-number-provenance "https://www.anpi-gabon.ga/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "CMR" {:name "Cameroon" :owner-authority "ARMP" :legal-basis "Code des marchés publics" :national-spec "e-procurement" :provenance "https://www.armp.cm/"
          :required-evidence ["RCCM/NIU record" "e-procurement registration" "RCCM extract" "Authorized-representative record"]}
   "CIV" {:name "Côte d'Ivoire" :owner-authority "ANRMP" :legal-basis "Code des marchés publics" :national-spec "e-procurement" :provenance "https://www.anrmp.ci/"
          :required-evidence ["RCCM record" "e-procurement registration" "RCCM extract" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))
