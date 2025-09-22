export interface AaeErrors{
  entityErrors: EntityErrors[];
}

export interface EntityErrors {
    entity?:                string;
    status?:                string;
    errors?:                Error[];
}

export interface Error {
    stagingId?:             string;
    identifier?:            string;
    key?:                   string;
    value?:                 string;
    description?:           string;
    error?:                 string;
    entity?:                 string;
}
