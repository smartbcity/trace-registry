package city.smartb.registry.program.bdd.exception

class NullDataTableParamException(
    param: String
): IllegalDataTableParamException(param, "Should not be null")
