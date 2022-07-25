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

    @MainParameter(displayName = "Hostname or IP address", description = "Specify a hostname or an IP address, e.g. www.example.com or 1.1.1.1")
    private String hostname;
    @FixedParam(param = "--report-wide", paramType = ParameterType.ONLY_PARAM)
    private Boolean report = true;
    
    @NumberParam(displayName = "Pings", param="--report-cycles", min=1., max=1000., step=1., description = "Set the number of pings sent", group = MtrPayload.GROUP_GENERAL)
    private Integer pings = 10;
    @BooleanParam(displayName = "Use TCP", param = "--tcp", paramType = ParameterType.ONLY_PARAM, description = "Ping over TCP instead of ICMP", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean useTcp = false;
    @BooleanParam(displayName = "Use UDP", param = "--udp", paramType = ParameterType.ONLY_PARAM, description = "Ping over UDP instead of ICMP", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean useUdp = false;
    @NumberParam(displayName = "Target port", param="--port", min=1., max=65536., step=1., description = "Target port number for TCP, SCTP, or UDP", group = MtrPayload.GROUP_GENERAL)
    private Integer port;
    @BooleanParam(displayName = "No DNS", param = "--no-dns", paramType = ParameterType.ONLY_PARAM, description = "Do not resolve host names", labelFalse = "Resolve", labelTrue = "Do not resolve", group = MtrPayload.GROUP_GENERAL)
    private Boolean noDns = false;
    @BooleanParam(displayName = "Show IPs", param = "--show-ips", paramType = ParameterType.ONLY_PARAM, description = "Show IP addresses in addition to host names", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean showIps = false;
    @BooleanParam(displayName = "AS Lookup", param = "--aslookup", paramType = ParameterType.ONLY_PARAM, description = "Display AS number", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_GENERAL)
    private Boolean asLookup = false;

    @BooleanParam(displayName = "Use SCTP", param = "--sctp", paramType = ParameterType.ONLY_PARAM, description = "Use Stream Control Transmission Protocol packets instead of ICMP ECHO.", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_ADVANCED)
    private Boolean useSctp = false;
    @BooleanParam(displayName = "IPv4 only", param="-4", description = "Use IPv4 only", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_ADVANCED)
    private Boolean v4 = false;
    @BooleanParam(displayName = "IPv6 only", param="-6", description = "Use IPv6 only. (IPv4 may be used for DNS lookups.)", labelFalse = "No", labelTrue = "Yes", group = MtrPayload.GROUP_ADVANCED)
    private Boolean v6 = false;
    @NumberParam(displayName = "Timeout", param="--timeout", min=1., max=600., step=1., description = "The number of seconds to keep probe sockets open before giving up on the connection. Using large values for this, especially combined with a short interval, will use up a lot of file descriptors", group = MtrPayload.GROUP_ADVANCED)
    private Integer timeout;
    @NumberParam(displayName = "Interval", param="--interval", min=1., max=600., step=1., description = "The positive no. of seconds between ICMP ECHO requests", group = MtrPayload.GROUP_ADVANCED)
    private Integer interval;
    @NumberParam(displayName = "First TTL", param="--first-ttl", min=1., max=600., step=1., description = "Specifies the maximum number of hops (max time-to-live value) traceroute will probe", group = MtrPayload.GROUP_ADVANCED)
    private Integer firstTtl;
    @NumberParam(displayName = "Max TTL", param="--max-ttl", min=1., max=600., step=1., description = "Set the timeout for each ping", group = MtrPayload.GROUP_ADVANCED)
    private Integer maxTtl;
    @NumberParam(displayName = "Packet size", param="--psize", min=1., max=600., step=1., description = "This option sets the packet size used for probing. It is in bytes, inclusive IP and ICMP headers.", group = MtrPayload.GROUP_ADVANCED)
    private Integer psize;

    @Override
    public String getCacheString() {
        return hostname;
    }
}