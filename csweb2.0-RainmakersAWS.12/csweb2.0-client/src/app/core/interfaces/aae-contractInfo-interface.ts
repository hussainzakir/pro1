export interface AaeContract{

    rates?:              ContractRates[];
    contract?:           ContractInfo[];
}

export interface ContractRates {
    site?:                string;
    containerGroup?:      string;
    effectiveDate?:       string;
    stopCode?:            string;
    rateType?:            string;
    districtCode?:        string;
    chargeCode?:          string;
    chargeRate?:          string;
    chargeType?:          string;
    chargeMethod?:        string;
    rates?:               string;
}

export interface ContractInfo {
   contractNumber?:       string;
   contractGroupNumber?:  string;
   contractName?:         string;
   contractGroupName?:    string;
   contract?:             string;
} 
