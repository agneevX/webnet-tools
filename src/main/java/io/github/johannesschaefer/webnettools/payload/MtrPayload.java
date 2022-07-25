package io.github.johannesschaefer.webnettools.payload;

import io.github.johannesschaefer.webnettools.annotation.*;
import lombok.Data;

@Data
@Tool(name="mtr", displayName="MTR", cmd="mtr", description="Network diagnostic tool combining traceroute and ping")
@Group(name=MtrPayload.GROUP_GENERAL)
@Group(name=MtrPayload.GROUP_ADVANCED)
public class MtrPayload implements Payload {

    public static final String GROUP_GENERAL = "General options";
    public static final String GROUP_ADVANCED = "Advanced options";

    @MainParameter(displayName = "Hostname or IP address", description = "Specify a hostname or an IP address")
    private String hostname;
    @FixedParam(param = "--report-wide", paramType = ParameterType.ONLY_PARAM)
    private Boolean report = true;
    
    @NumberParam(displayName = "Pings", param="--report-cycles", min=1., max=1000., step=1., description = "Set the number of pings sent", group = MtrPayload.GROUP_GENERAL)
    private Integer pings = 10;
    @BooleanParam(displayName = "Use TCP", param = "--tcp", paramType = ParameterType.ONLY_VALUE, description = "Use UDP pings instead of ICMP echo", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean useTcp = false;
    @BooleanParam(displayName = "Use UDP", param = "--udp", paramType = ParameterType.ONLY_VALUE, description = "Use UDP pings instead of ICMP echo", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean useUdp = false;
    @NumberParam(displayName = "Port", param="--port", min=1., max=65536., step=1., description = "Target port number for TCP, SCTP, or UDP", group = MtrPayload.GROUP_GENERAL)
    private Integer port;
    @BooleanParam(displayName = "No DNS", param = "--no-dns", paramType = ParameterType.ONLY_PARAM, description = "Do not resolve host names", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean noDns = false;
    @BooleanParam(displayName = "AS Lookup", param = "--aslookup", paramType = ParameterType.ONLY_PARAM, description = "Display AS number", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean asLookup = false;

    @BooleanParam(displayName = "Use IPv4 only", param="-4", description = "Use IPv4 only.", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_ADVANCED)
    private Boolean v4 = false;
    @BooleanParam(displayName = "Use IPv6 only", param="-6", description = "Use IPv6 only. (IPV4 may be used for DNS lookups.)", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_ADVANCED)
    private Boolean v6 = false;
    @BooleanParam(displayName = "Show IPs", param = "--show-ips", paramType = ParameterType.ONLY_PARAM, description = "Show IP addresses in addition to host names", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_ADVANCED)
    private Boolean showIps = false;
    @NumberParam(displayName = "Timeout", param="--timeout", min=1., max=60., step=1., description = "The number of seconds to keep probe sockets open before giving up on the connection. Using large values for this, especially combined with a short interval, will use up a lot of file descriptors", group = MtrPayload.GROUP_ADVANCED)
    private Integer timeout;
    @NumberParam(displayName = "First TTL", param="--first-ttl", min=1., max=60., step=1., description = "Specifies the maximum number of hops (max time-to-live value) traceroute will probe.", group = MtrPayload.GROUP_ADVANCED)
    private Integer firstTtl;
    @NumberParam(displayName = "Max TTL", param="--max-ttl", min=1., max=60., step=1., description = "Set the timeout for each ping", group = MtrPayload.GROUP_ADVANCED)
    private Integer maxTtl;

    @Override
    public String getCacheString() {
        return hostname;
    }
}