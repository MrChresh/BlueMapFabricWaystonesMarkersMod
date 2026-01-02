package com.chresh.bluemapfabricwaystonemarkersmod.entity;

import java.util.List;

import com.chresh.bluemapfabricwaystonemarkersmod.markers.MarkerGroup;
import com.chresh.bluemapfabricwaystonemarkersmod.util.ParsingContext;

public class WaystoneLinesParser {
    private enum ParseStates {
        START,
        HAS_MARKER_TYPE,
        INVALID
    }

    private final List<MarkerGroup> markerGroups;

    public WaystoneLinesParser(List<MarkerGroup> markerGroups) {
        this.markerGroups = markerGroups;
    }

    public WaystoneLinesParseResult parse(String[] lines) {
        var state = ParseStates.START;

        var context = new ParsingContext();

        for (String line : lines) {
            line = line.trim();
            if (state == ParseStates.START) {
                state = processStartState(line, context, markerGroups);
            } else if (state == ParseStates.HAS_MARKER_TYPE) {
                processHasMarkerType(line, context);
            }
        }

        return state == ParseStates.INVALID
                ? new WaystoneLinesParseResult(null, "", "")
                : context.buildResult();
    }

    private static ParseStates processStartState(
            String line,
            ParsingContext context,
            List<MarkerGroup> markerGroups) {
        if (line.isEmpty()) {
            return ParseStates.START;
        }

        context.setMarkerGroup(getMarkerGroup(line, markerGroups));
        if (context.getMarkerGroup() == null) {
            return ParseStates.INVALID;
        }

        context.setLabel(line.substring(context.getMarkerGroup().prefix().length()).trim());
        if (!context.getLabel().isEmpty()) {
            context.appendDetail(context.getLabel());
        }
        return ParseStates.HAS_MARKER_TYPE;
    }

    private static void processHasMarkerType(String line, ParsingContext context) {
        if (line.isEmpty()) {
            return;
        }

        if (context.getLabel().isEmpty()) {
            context.setLabel(line);
        }

        context.appendDetail(line);
    }

    private static MarkerGroup getMarkerGroup(String line, List<MarkerGroup> markerGroups) {
        return markerGroups.stream()
                .filter(markerGroup -> line.startsWith(markerGroup.prefix()))
                .findFirst()
                .orElse(null);
    }
}
