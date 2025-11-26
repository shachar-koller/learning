class Person:
    id: str
    gender: str        # 'M' or 'F'
    alive: bool
    father: 'Person | None'
    mother: 'Person | None'
    children: list['Person']
    spouse: 'Person | None'
    is_bechor: bool    # optional, for later

class FamilyGraph:
    persons: dict[str, Person]
