from collections import deque

def nearest_descendants_bfs(roots: list[Person]) -> list[Person]:
    """
    BFS downwards from roots to find all alive descendants
    at the minimal distance from the roots.
    Returns the set (list) of people at that minimal distance.
    If no alive descendants at all → [].
    """
    if not roots:
        return []

    q = deque()
    visited = set()

    for child in roots:
        q.append((child, 1))      # distance from the parent
        visited.add(child.id)

    min_dist = None
    result = []

    while q:
        node, dist = q.popleft()

        # If this node is alive, it’s a candidate heir
        if node.alive:
            if min_dist is None:
                min_dist = dist
            if dist == min_dist:
                result.append(node)
            elif dist > min_dist:
                # We already found a closer layer; classic BFS cutoff
                break

        # Continue exploring deeper descendants
        for c in node.children:
            if c.id not in visited:
                visited.add(c.id)
                q.append((c, dist + 1))

    return result


def descendants_heirs(person: Person) -> dict[Person, float]:
    """
    Return a mapping {heir: share} for this person's estate
    *among his descendants only*, following:
      1) sons and their descendants first
      2) if that fails, daughters and their descendants
    Shares are given as normalized fractions (sum to 1.0).
    For simplicity, we:
      - ignore bechor double portion
      - treat all branches equal once their class is chosen
    """

    sons = [c for c in person.children if c.gender == 'M']
    daughters = [c for c in person.children if c.gender == 'F']

    # --- Stage 1: male-line (sons and their descendants) ---
    if sons:
        male_heirs = nearest_descendants_bfs(sons)
        if male_heirs:
            # All male_heirs are at same minimal distance.
            # Real halacha does "representation" by branches,
            # but for simplicity we just split equally here.
            n = len(male_heirs)
            return {h: 1.0 / n for h in male_heirs}

    # --- Stage 2: female-line (daughters and their descendants) ---
    if daughters:
        female_heirs = nearest_descendants_bfs(daughters)
        if female_heirs:
            n = len(female_heirs)
            return {h: 1.0 / n for h in female_heirs}

    # No descendants at all
    return {}


def halachic_yerusha(niftar: Person) -> dict[Person, float]:
    """
    Simplified halachic yerusha algorithm.

    Order:
    1) If the deceased is a woman and her husband is alive → he inherits (simplified).
    2) Descendants: sons & their descendants, then daughters & their descendants.
    3) If no descendants: go up paternal line (father, grandfather, etc.),
       using "mishmush": the ancestor inherits in the grave and his heirs
       (computed by the same rules) get the estate.
    """

    # 1) Super-simplified husband rule
    if niftar.gender == 'F' and niftar.spouse and niftar.spouse.alive:
        # In reality, this interacts with kids / nichsei tzon barzel etc.
        # We just model a "husband gets all" switch.
        return {niftar.spouse: 1.0}

    # 2) Try descendants
    heirs = descendants_heirs(niftar)
    if heirs:
        return heirs

    # 3) No descendants → climb paternal line
    ancestor = niftar.father

    while ancestor is not None:
        if ancestor.alive:
            # Father (or further ancestor) is alive → he gets everything
            return {ancestor: 1.0}

        # Ancestor is dead → "inherit in grave" and pass to HIS heirs
        ancestor_heirs = descendants_heirs(ancestor)
        if ancestor_heirs:
            # Whatever his heirs would get from *his* estate,
            # now receive the niftar's estate instead.
            return ancestor_heirs

        # If this ancestor has no descendants either,
        # climb to the next ancestor up (grandfather, etc.)
        ancestor = ancestor.father

    # Reached top of paternal line with no heirs found: degenerate case
    return {}
