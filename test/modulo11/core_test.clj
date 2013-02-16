(ns modulo11.core-test
  (:use clojure.test
        modulo11.core))

(deftest test-validate-string
  (testing "'validate-string' mit Buchungszeichen ohne Trennpunkte"
    (is (= (validate-string "530666001001") true))
    (is (= (validate-string "53066600d001") false))
    (is (= (validate-string "53066600d0012") false)))
  (testing "'validate-string' mit Buchungszeichen mit Trennpunkten"
    (is (= (validate-string "5.3066.600100.1") true))
    (is (= (validate-string "5.3066.60010f.1") false))
    (is (= (validate-string "5.3066.6001030.1") false))))

(deftest test-validate-number
  (testing "'validate-number'"
    (is (= (validate-number 5) true))
    (is (= (validate-string "f") false))))

(deftest test-create-ag
  (testing "'create-ag'"
    (is (= (create-ag "5.3066.600101.9") "530666001027"))
    (is (= (create-ag "530666001019") "530666001027"))))

(deftest test-without-checksum
  (testing "'without-checksum'"
    (is (= (without-checksum "5.3066.600101.9") 53066600101))
    (is (= (without-checksum "530666001019") 53066600101))))

(deftest test-calc-checksum
  (testing "'calc-checksum'"
    (is (= (calc-checksum "53066600101") 9))))

(deftest test-number-list
  (testing "'calc-checksum'"
    (is (= (number-list "1234") (list 1 2 3 4)))))

(deftest test-add-points
  (testing "'calc-checksum'"
    (is (= (add-points "530666001019\n530666001027") "5.3066.600101.9\n5.3066.600102.7"))))

(deftest test-next-bz
  (testing "'next-bz' ohne Trennpunkte"
    (is (= (next-bz "530666001019") "5.3066.600102.7"))
    (is (= (next-bz "530666001019" 2) "5.3066.600102.7\n5.3066.600103.5")))
  (testing "'next-bz' mit Trennpunkten"
    (is (= (next-bz "5.3066.600101.9") "5.3066.600102.7"))
    (is (= (next-bz "5.3066.600101.9" 2) "5.3066.600102.7\n5.3066.600103.5"))))

(deftest test-check-bz
  (testing "'check-bz' ohne Trennpunkte"
    (is (= (check-bz "530666001019") true))
    (is (= (check-bz "530666001010") false)))
  (testing "'check-bz' mit Trennpunkte"
    (is (= (check-bz "5.3066.600101.9") true))
    (is (= (check-bz "5.3066.600101.0") false))))

