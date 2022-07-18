package io.github.johannesschaefer.webnettools.payload;

import io.github.johannesschaefer.webnettools.annotation.*;
import lombok.Data;

@Data
@Tool(name="mtr", displayName="MTR", cmd="mtr", description="Network diagnostic tool combining 'traceroute' and 'ping'")
public class MtrPayload implements Payload {
    @MainParameter(displayName = "Hostname or IP address", description = "Specify a hostname or an IP address")
    private String hostname;

    @FixedParam(param = "--report-wide", paramType = ParameterType.ONLY_PARAM)
    private Boolean report = true;
    
    @NumberParam(displayName = "Pings", param="--report-cycles", min=1., max=1000., step=1., description = "Set the number of pings sent")
    private Integer pings;
       
    @BooleanParam(displayName = "Use UDP", param = "--udp", paramType = ParameterType.ONLY_PARAM, description = "Use UDP instead of ICMP echo", labelTrue = "Yes", labelFalse = "No")
    private Boolean useUdp;
    
    @BooleanParam(displayName = "Use TCP", param = "--tcp", paramType = ParameterType.ONLY_PARAM, description = "Use TCP instead of ICMP echo", labelTrue = "Yes", labelFalse = "No")
    private Boolean useTcp;
    
    @NumberParam(displayName = "Port", param="--port", min=1., max=65536., step=1., description = "Target port number for TCP, SCTP, or UDP")
    private Integer port;
    
    @BooleanParam(displayName = "DNS lookups", param = "--no-dns", paramType = ParameterType.ONLY_PARAM, description = "Do not resolve host names", labelTrue = "Do not resolve", labelFalse = "Resolve")
    private Boolean noDns = false;
    
    @BooleanParam(displayName = "AS Lookup", param = "--aslookup", paramType = ParameterType.ONLY_PARAM, description = "Display AS number", labelTrue = "Yes", labelFalse = "No")
    private Boolean asLookup = false;
    
    @BooleanParam(displayName = "Show IPs", param = "--show-ips", paramType = ParameterType.ONLY_PARAM, description = "Show IP addresses in addition to host names", labelTrue = "Yes", labelFalse = "No")
    private Boolean showIps = false;

    @Override
    public String getCacheString() {
        return hostname;
    }
}
